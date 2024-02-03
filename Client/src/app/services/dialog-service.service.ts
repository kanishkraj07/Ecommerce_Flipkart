import {Injectable, signal, WritableSignal} from '@angular/core';
import {Dialog} from "../models/dialog";
import {BehaviorSubject, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DialogService {

 private _dialog: BehaviorSubject<Dialog> = new BehaviorSubject<Dialog>({} as Dialog);

  constructor() { }


  get dialog(): BehaviorSubject<Dialog> {
    return this._dialog;
  }

  openDialog(id: string, params?: string) {
    const dialogDetails: Dialog = { id: id, params: params } as Dialog;
    this._dialog.next(dialogDetails);
  }
}
