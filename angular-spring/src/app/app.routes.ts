import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/components/home/home.component';
import { NotFoundComponent } from './shared/components/not-found/not-found.component';

export const routes: Routes = [
    {
        path: "home",
        component: HomeComponent,
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
