import { Component, OnInit } from '@angular/core';
import { User } from '../Model/User.model';
import { Respond } from '../Model/Respond.model';
import { CapstoreService } from '../service/capstore.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userDetail = new User();
  data;
  role;
  error;
  constructor(private _capstoreService: CapstoreService, private _router: Router) { }
  ngOnInit(): void {
    localStorage.removeItem("customer");
    localStorage.removeItem("merchant");
    localStorage.removeItem('loggedIn');
  }
  onSubmit(form) {
    if (this.role == "Merchant") {
      console.log(this.userDetail);
      this._capstoreService.registerMerchant(this.userDetail).subscribe(
        (error) => {
          console.log(error);
          if (error == null)
            this.error = false;
          else
            this._capstoreService.setCurrentMerchant(error);
        });
    }
    else {
      console.log(this.userDetail);
      this._capstoreService.getCustomer(this.userDetail.email, this.userDetail.password).subscribe(
        (error) => {
          console.log(error);
          this.data = error.error;
          if (this.data == "true")
            this.error = error.message;
          else {
            console.log(error.object);
            this._capstoreService.setCurrentCustomer(error.object);
            this._capstoreService.setLoggedIn();
            this._router.navigate(['\homepage']);

          }
        });
    }
  }
}
