import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../Model/User.model';
import { Product } from '../Model/Product.model';
@Injectable({
  providedIn: 'root'
})
export class CapstoreService {
  private baseUrl = "http://localhost:8080/api1";
  custModel = new User();
  private loggedInStatus = JSON.parse(localStorage.getItem('loggedIn') || 'false');
  constructor(private http: HttpClient) { }
  get isLoggedIn() {
    return JSON.parse(localStorage.getItem('loggedIn') || this.loggedInStatus.toString());
  }

  public setLoggedIn() {
    this.loggedInStatus = true;
    localStorage.setItem('loggedIn', 'true');
  }
  public getLoggedIn() {
    console.log(!!localStorage.getItem('loggedIn'));
    return !!localStorage.getItem('loggedIn');
  }
  public setCurrentCustomer(user) {
    localStorage.setItem("customer", JSON.stringify(user));

  }
  public getCurrentCustomer() {
    return localStorage.getItem("customer");
  }
  public setCurrentMerchant(user) {
    localStorage.setItem("merchant", JSON.stringify(user));

  }
  public getCurrentMerchant() {
    return localStorage.getItem("merchant");
  }
  public setCurrentProductPage(page) {
    localStorage.setItem("product", JSON.stringify(page));

  }
  public getCurrentProductPage() {
    return localStorage.getItem("product");

  }
  public registerCustomer(customer: Object): Observable<any> {
    console.log(customer);
    return this.http.post(this.baseUrl + '/registerCustomer', customer);
  }
  public registerMerchant(merchant: Object): Observable<any> {
    return this.http.post(this.baseUrl + '/registerMerchant', merchant);
  }
  public getCustomer(email: any, password: any) {
    const params = {
      email: email,
      password: password
    }
    return this.http.get(this.baseUrl + '/login', { params });
  }
  public forgotPassword(email) {
    return this.http.get(this.baseUrl + '/forgotpassword/' + email);
  }
  public getAllProduct(): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl + '/allproducts');
  }
  public getSpecificProduct(prod): Observable<Product[]> {
    console.log(JSON.parse(this.getCurrentProductPage()));
    return this.http.get<Product[]>(this.baseUrl + '/productcategory/' + prod);
  }
  public getDiscountedProduct(prod): Observable<Product[]> {
    console.log(JSON.parse(this.getCurrentProductPage()));
    return this.http.get<Product[]>(this.baseUrl + '/discountcategory/' + prod);
  }



}
