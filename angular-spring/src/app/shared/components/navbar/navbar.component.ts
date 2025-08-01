import { heroUser, heroUserCircle, heroUsers } from '@ng-icons/heroicons/outline';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { NgIcon, provideIcons } from '@ng-icons/core';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, NgIcon],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
  providers: [provideIcons({
    heroUserCircle
  })]
})
export class NavbarComponent {
  public iconColor = '#000000';
}
