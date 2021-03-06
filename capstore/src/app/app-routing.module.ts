import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import{SignupComponent} from './signup/signup.component';
import { AuthGuard } from './guards/auth.guard';
import {HomepageComponent} from './homepage/homepage.component';
import { ProductPageComponent } from './ProductPage/productpage.component';
  import { ForgotPasswordComponent } from './forgotpassword/forgotpassword.component';




const routes: Routes = [
  {path:'',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'homepage',component:HomepageComponent,canActivate:[AuthGuard]},
  {path:'productpage',component:ProductPageComponent,canActivate:[AuthGuard]},
  {path:'forgotpassword',component:ForgotPasswordComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const RoutingComponents=[LoginComponent,SignupComponent,HomepageComponent,ProductPageComponent,ForgotPasswordComponent];
