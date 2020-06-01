import { Component, OnInit } from '@angular/core';
import { CapstoreService } from '../service/capstore.service';
import { Router } from '@angular/router';

@Component({
    selector: "forgotpassword",
    templateUrl: "./forgotpassword.component.html",
    styleUrls: ["./forgotpassword.component.css"],
})
export class ForgotPasswordComponent implements OnInit {
    eMail;
    submitted = false;
    message;
    constructor(
        private _capstoreService: CapstoreService,
        private router: Router
    ) { }

    ngOnInit(): void { }
    onSubmit(form) {
        this.submitted = true;
        this._capstoreService
            .forgotPassword(this.eMail)
            .subscribe((error) => {
                console.log(error);

                this.message = error.message;
            });
    }
}
