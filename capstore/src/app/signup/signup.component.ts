import { Component, OnInit } from '@angular/core';
import { User } from '../Model/User.model';
import { CapstoreService } from '../service/capstore.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  userDetails: User = new User();
  password;
  role;
  flag: boolean;
  constructor(private _capstoreService: CapstoreService) { }

  ngOnInit(): void {
  }

  validateUser() {
    this.userDetails.role = this.role;
    if (this.role == "Merchant") {
      console.log(this.userDetails);
      this._capstoreService.registerMerchant(this.userDetails).subscribe(
        (error) => {
          console.log(error)
          if (error.error == "true")
            this.flag = false;
          else
            this.flag = true;

        });
    }
    else {
      console.log(this.userDetails);
      this._capstoreService.registerCustomer(this.userDetails).subscribe(
        (data) => {
          console.log(data)
          if (data.error == "true")
            this.flag = false;
          else
            this.flag = true;
        });
    }
  }
}
