import { Routes } from '@angular/router';
import { NotFoundComponent } from './shared/components/not-found/not-found.component';
import { HomePageComponent } from './features/home/pages/home-page/home-page.component';

export const routes: Routes = [
    {
        path: "home",
        component: HomePageComponent,
        title: "Home"
    },
    {
        path: '',
        redirectTo: '/home',
        pathMatch: 'full'
    },
    {
        path: "**",
        component: NotFoundComponent
    }
];
