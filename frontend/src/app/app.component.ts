import {Component, OnDestroy, OnInit} from '@angular/core';
import {OAuthService} from "angular-oauth2-oidc";
//import {AppService} from "./app.service";
import {authConfig} from "./auth.config";
import {AppService} from "./app.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy{
  title = 'frontend';
  text = '';
  helloSubscription : Subscription | any;
  constructor(private oauthService:OAuthService, private appService:AppService) {

  }

  ngOnInit(): void {
    this.configure();
    this.helloSubscription = this.appService.hello().subscribe(res =>{
      this.text = res;
    })
    }
  login() {
  this.oauthService.initCodeFlow();
  }

  private configure(){
    this.oauthService.configure(authConfig);
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
  }
  logout() {
    this.oauthService.logOut();
  }

  ngOnDestroy(): void {
    this.helloSubscription.unsubscribe();
  }
}
