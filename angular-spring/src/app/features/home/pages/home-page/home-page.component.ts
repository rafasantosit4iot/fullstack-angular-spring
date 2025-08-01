import { Component } from '@angular/core';
import { NavbarComponent } from "../../../../shared/components/navbar/navbar.component";
import { FooterComponent } from "../../../../shared/components/footer/footer.component";
import { HeroComponent } from "../../components/hero/hero.component";

@Component({
  selector: 'app-home-page',
  imports: [NavbarComponent, FooterComponent, HeroComponent],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {

}
