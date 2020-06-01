import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { CapstoreService } from '../service/capstore.service';

@Component({
  selector: "app-homepage",
  templateUrl: "./homepage.component.html",
  styleUrls: ["./homepage.component.css"],
})
export class HomepageComponent implements OnInit {
  searchProduct;

  constructor(private route: Router, private _capstoreService: CapstoreService) { }
  ngOnInit() { }
  allProduct(event) {
    console.log(event.target.innerHTML);
    localStorage.removeItem("product");
    this._capstoreService.setCurrentProductPage(event.target.innerHTML);
    this.route.navigate(['/productpage']);
  }
  
  search() {}
  openNav() {
    document.getElementById("mySidebar").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
  }
  closeNav() {
    document.getElementById("mySidebar").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
  }
}