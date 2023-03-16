import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccueilComponent } from './accueil/accueil.component';
import { PartieCreateComponent } from './partie/partie-create/partie-create.component';
import { PartieDetailComponent } from './partie/partie-detail/partie-detail.component';
import { PartieListComponent } from './partie/partie-list/partie-list.component';
import { PartieMancheComponent } from './partie/partie-manche/partie-manche.component';

const routes: Routes = [
  { path: '', component:AccueilComponent },
  { path: 'create', component:PartieCreateComponent },
  { path: 'history/game/:id', component:PartieDetailComponent },
  { path: 'history', component:PartieListComponent},
  { path: 'create/:id/manche/:idManche', component:PartieMancheComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
