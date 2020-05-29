import { Component, OnInit } from '@angular/core';
import {User} from '../Model/User.model';
import {ConfirmEqualValidatorDirective} from '../Shared/confirm-equal-validator.directive';
import { CapstoreService } from '../service/capstore.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

 userDetails:User=new User();
 password;
 role;
 flag:boolean;
  constructor(private _capstoreService:CapstoreService) { }
validateUser()
{ this.userDetails.role=this.role;
  if(this.role=="Merchant")
  {
    console.log(this.userDetails);
  this._capstoreService.registerMerchant(this.userDetails).subscribe(
    (error) => {console.log(error)
     this.flag=error;

    });
  }
  else{
    console.log(this.userDetails);
    this._capstoreService.registerCustomer(this.userDetails).subscribe(
    (data) => {console.log(data)
      this.flag=data;
      }  );
  }
  }

  ngOnInit(): void {
  }


}
