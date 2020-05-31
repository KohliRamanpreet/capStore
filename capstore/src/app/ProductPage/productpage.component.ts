import { Component, OnInit } from '@angular/core';
import {User} from '../Model/User.model';
import { CapstoreService } from '../service/capstore.service';
import { Router } from '@angular/router';
import { Product } from '../Model/Product.model';

@Component({
  selector: 'app-productpage',
  templateUrl: './productpage.component.html',
  styleUrls: ['./productpage.component.css']
})
export class ProductPageComponent implements OnInit {
 product:Product[];
 prod;
  constructor(private _capstoreService:CapstoreService,private router:Router) { }

  ngOnInit(): void {
      
   //  this._capstoreService.getAllProduct().subscribe(prod=>this.product=prod);
   this.prod=JSON.parse(this._capstoreService.getCurrentProductPage());

this._capstoreService.getSpecificProduct(this.prod)
.subscribe(prod=>this.product=prod);

  }



}
