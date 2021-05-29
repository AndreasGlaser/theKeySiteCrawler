import { Injectable } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { catchError, tap, switchAll } from 'rxjs/operators';
import { EMPTY, Subject } from 'rxjs';
export const WS_ENDPOINT = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private socket$: WebSocketSubject<any> | undefined;
  private messagesSubject$ = new Subject();
  // @ts-ignore
  public messages$ = this.messagesSubject$.pipe(switchAll(), catchError(e => { throw e }));

  public connect(): void {

    if (!this.socket$ || this.socket$.closed) {
      this.socket$ = this.getNewWebSocket();
      const messages = this.socket$.pipe(
        tap({
          error: error => console.log(error),
        }), catchError(_ => EMPTY));
      this.messagesSubject$.next(messages);
    }
  }

  private getNewWebSocket() {
    return webSocket(WS_ENDPOINT);
  }
  sendMessage(msg: any) {
    if(this.socket$){
      this.socket$.next(msg);
    }

  }
  close() {
    if(this.socket$){
      this.socket$.complete();
    }
  }
}

