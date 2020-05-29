import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../Model/User.model';
@Injectable({
  providedIn: 'root'
})
export class CapstoreService {
  private baseUrl = "http://localhost:8083/api1";
  custModel=new  User();
private loggedInStatus=JSON.parse(localStorage.getItem('loggedIn')||'false');
  constructor(private http:HttpClient) { }
  public registerCustomer(customer: Object): Observable<any> {
    console.log(customer);
    return this.http.post(this.baseUrl+'/registerCustomer',customer);
  }
  public registerMerchant(merchant:Object):Observable<any>{
    return this.http.post(this.baseUrl+'/registerMerchant', merchant);
  }
  get isLoggedIn()
  {
return JSON.parse(localStorage.getItem('loggedIn')|| this.loggedInStatus.toString());
  }
  setLoggedIn(value:boolean)
  {
    this.loggedInStatus=value;
    localStorage.setItem('loggedIn','true');
  }
  setCurrentCustomer(user){
    localStorage.setItem("customer", JSON.stringify(user));
  
  }
getCurrentCustomer()
  {
  return localStorage.getItem("customer");
  } 
  setCurrentMerchant(user){
    localStorage.setItem("merchant", JSON.stringify(user));
  
  }
getCurrentMerchant()
  {
  return localStorage.getItem("merchant");
  } 
  /* getMerchant(): Observable<any> {
    return this.http.get<any>(this.baseUrl+"/confirm-account");
  }
  getCustomer(): Observable<any> {
    return this.http.get<any>(this.baseUrl+"/confirm-account");
  } */
  getCustomer(email:any,password:any)
  {
    const params = {
      email: email,
      password: password
    }
    return this.http.get(this.baseUrl+'/login', {params});

  }
getUserDetail()
{
  return "ram@gmail.com";
}
getSomeData()
{
  return "bhaak";
  
}
}
