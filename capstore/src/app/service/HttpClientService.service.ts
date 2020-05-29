import { Injectable, ErrorHandler } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

export class Product{
  constructor(
    public productId:number,
    public productName:string,
    public productImage:string,
    public productPrice:number,
    public productRating:number,
    public noOfProductViewed:number,
    public productBrand:string,
    public noOfProducts:number,
    public productInfo:string,
    public discount:number,
    public productCategory:string,
    public productActivated:boolean,
    public status:boolean,
    public featured:boolean,
    public userId:number
){}
}
@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(private httpClient:HttpClient) { }

  

  getCategory(category){
    return this.httpClient.get<Product[]>('http://localhost:7077/merchant/productCategory/'+category);
  }
  getAllProducts(){
   return this.httpClient.get<Product[]>('http://localhost:7077/merchant/allProducts');
 }
 getDiscount(category,discountPercent):Observable<any>{
   return this.httpClient.get<Product[]>('http://localhost:7077/merchant/discountCategory/'+category+'/'+discountPercent);
 }
 getSearchProducts(category){
   return this.httpClient.get<Product[]>('http://localhost:7077/merchant/searchProducts/'+category);
 }

 getProduct(id){
   return this.httpClient.get<Product>(
    //  provide url for getting single product
    id
   );
 }
}