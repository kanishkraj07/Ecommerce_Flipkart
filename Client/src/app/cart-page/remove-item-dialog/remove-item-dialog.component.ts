import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {DialogService} from "../../services/dialog-service.service";
import {Dialog} from "../../models/dialog";
import {tap} from "rxjs";

@Component({
  selector: 'app-remove-item-dialog',
  templateUrl: './remove-item-dialog.component.html',
  styleUrls: ['./remove-item-dialog.component.less']
})
export class RemoveItemDialogComponent implements OnInit{
  @Input('openRemoveDialog') openRemoveDialog: boolean;
  @Input('dialogId') dialogId: string;
  @Output() removeItemEvent: EventEmitter<any> = new EventEmitter();
  @ViewChild('REMOVE_DIALOG') remove_dialog: ElementRef;

  dialogDetails: Dialog;

  constructor(private dialogService: DialogService) {
  }

  ngOnInit(): void {
    this.dialogService.dialog.asObservable().pipe(
      tap((details: Dialog) => {
        if(details.id === this.dialogId) {
          this.dialogDetails = details;
          this.remove_dialog?.nativeElement?.showModal();
        }
      })).subscribe();
   }

  removeItem() {
    this.removeItemEvent.emit(this.dialogDetails.params);
    this.closeRemoveDialog();
  }

  closeRemoveDialog() {
    this.remove_dialog.nativeElement.close();
  }
}
