import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule, RoutingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { CapstoreService } from './service/capstore.service';
import { HttpClientModule } from '@angular/common/http';
import { ConfirmEqualValidatorDirective } from './Shared/confirm-equal-validator.directive'
import { AuthGuard } from './guards/auth.guard';
@NgModule({
  declarations: [
    AppComponent,
    ConfirmEqualValidatorDirective,
    RoutingComponents
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,

  ],
  providers: [CapstoreService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
