import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  ElementRef, OnDestroy,
  OnInit,
  QueryList,
  ViewChild,
  ViewChildren
} from '@angular/core';
import {Form, FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthHttpService} from "../services/auth-http.service";
import {catchError, finalize, Observable, of, switchMap, tap} from "rxjs";
import {UserAuthInfo} from "../models/otp-auth";
import {AccountHttpService} from "../services/account-http.service";
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit, AfterViewInit, OnDestroy{
  styleChange: string;
  currentView: string;
  authenticationFormGroup: FormGroup;

  errors: string;
  authView: boolean;
  otpDigitIndex: number = 0;
  enableOtpAuthView: boolean = false;
  otpDigits: number[] = new Array(6);

  otpDigitForm: FormGroup;
  isAccountRegistered: boolean;
  currentTime: number;
  futureTime: number;
  resendCodeTimer: number;
  resendCodeInterval;
  isResendTimerStopped: boolean;

  readonly NEW_USER_MESSAGE = `Looks like you're new here!`;

  @ViewChildren('otpDigit') otpDigitInputFields: QueryList<ElementRef<HTMLInputElement>>;

  constructor(private formBuilder: FormBuilder,
              private route: Router,
              private activatedRoute: ActivatedRoute,
              private authHttpService: AuthHttpService,
              private accountHttpService: AccountHttpService,
              private authenticationService: AuthenticationService) {
  }

  get otpDigitControls() {
    return (<FormArray>this.otpDigitForm.get('otpDigitFields'))?.controls;
  }

  get buildControllers(): Array<FormControl> {
    const formControlsArray: FormControl[] = [];
    for(let i=0; i<6; i++) {
      formControlsArray.push(new FormControl())
    }
    return formControlsArray;
  }

  ngOnInit() {
    this.currentView = this.activatedRoute.snapshot.queryParams['login'] ? 'loginView' : 'signupView';
    this.authenticationFormGroup = this.formBuilder.group({
      mobileNumber: this.formBuilder.control('', [Validators.required,
        Validators.pattern('[0-9]*'), Validators.minLength(10), Validators.maxLength(10)])
    });
    this.otpDigitForm = this.formBuilder.group({
      otpDigitFields: this.formBuilder.array(this.buildControllers)
    });
  }

  changePlaceholderStyle() {
    this.styleChange = 'input-placeholder';
  }

  changeView() {
    const redirectViewParams = this.currentView === 'loginView' ? {queryParams: {signup: true}} : {queryParams: {login:true}};
    this.route.navigate(['/account'], redirectViewParams).then(() => {
      window.location.reload();
    });
  }

  requestOtp(): void {
    if(this.authenticationFormGroup.valid) {
      const mobileNo: number = this.authenticationFormGroup.get('mobileNumber').value;

      this.checkAccountRegistration(mobileNo).pipe(
        switchMap(() => {
          if(this.currentView === 'loginView') {
            if (this.isAccountRegistered) {
             return this.sendOtp(mobileNo);
            } else {
              this.errors = 'mobile number not registered';
              this.changeView();
            }
          } else {
            if (!this.isAccountRegistered) {
             return this.sendOtp(mobileNo);
            } else {
              this.errors = 'mobile number already registered'
              this.changeView();
            }
          }
          return of({});
        }),
      ).subscribe();
    }
    else {
      this.errors = 'Please enter a valid Mobile number';
    }
  }

  checkAccountRegistration(mobileNo: number) {
    return this.accountHttpService.isAccountRegistered(mobileNo).pipe(
      tap((response: boolean) => {
      this.isAccountRegistered = response;
    }));
  }

  sendOtp(mobileNo: number): Observable<unknown> {
    return this.authHttpService.sendOtp(mobileNo).pipe(
      finalize(() =>  {
        this.authView = true;
        this.startResendCodeTimer();
      })
    );
  }

  resendOtp(mobileNo: number) {
    this.sendOtp(mobileNo).subscribe();
  }

  shiftFocus(event: any): void {
    if(event.code.toString().includes('Digit') || event.code.toString().includes('Numpad') || event.code === 'ArrowRight') {
      if (this.otpDigitIndex < this.otpDigitInputFields.length) {
        this.otpDigitInputFields.get(this.otpDigitIndex + 1)?.nativeElement.focus();
        this.otpDigitIndex++;
      }
    }
  }

  backFocus(event: any): void {
    if(event.target.value.length === 0 && this.otpDigitIndex > 0) {
      const prevDigitRef: HTMLInputElement = this.otpDigitInputFields.get(this.otpDigitIndex - 1)?.nativeElement as HTMLInputElement;
      prevDigitRef.focus();
      prevDigitRef.value = '';
      this.otpDigitIndex--;
    }
  }

  verifyOtp() {
    const allDigits: string[] = this.otpDigitForm.value.otpDigitFields;
    const otpAuthInfoRequest: UserAuthInfo = {mobile_no: `${this.authenticationFormGroup.get('mobileNumber')?.value}`, otp_no: allDigits.join('')} as UserAuthInfo
    this.authHttpService.verifyOtp(otpAuthInfoRequest, this.currentView !== 'loginView').pipe(
      tap((authResponse: string) => {
        this.authenticationService.setAuthenticationDetails(authResponse);
      })
    ).subscribe();
  }

  startResendCodeTimer() {
    clearInterval(this.resendCodeInterval);
    this.isResendTimerStopped = false;
    this.futureTime = new Date().getTime() + 1000 * 15;
    this.resendCodeTimer = this.futureTime - new Date().getTime();
     this.resendCodeInterval = setInterval(() => {
        this.resendCodeTimer = this.resendCodeTimer - 1000;
      if(this.resendCodeTimer/1000 <= 0) {
        this.isResendTimerStopped = true;
        clearInterval(this.resendCodeInterval);
      }
    }, 1000);
  }

  ngAfterViewInit(): void {
    this.otpDigitInputFields.get(0)?.nativeElement.focus();
  }

  ngOnDestroy(): void {
  }
}
