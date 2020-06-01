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
 p;
  constructor(private _capstoreService:CapstoreService,private router:Router) { }

  ngOnInit(): void {
   this.prod=JSON.parse(this._capstoreService.getCurrentProductPage());
if(this.prod=="All Products")
this._capstoreService.getAllProduct().subscribe(prod=>this.product=prod);
else if(this.prod=="10%" || this.prod=="20%" ||this.prod=="30%" )
{
  console.log(this.prod);
  this.p=this.prod[0]+this.prod[1];
  console.log(this.p);
this._capstoreService.getDiscountedProduct(this.p).subscribe(prod=>this.product=prod);
}
else
this._capstoreService.getSpecificProduct(this.prod)
.subscribe(prod=>this.product=prod);
}
}
