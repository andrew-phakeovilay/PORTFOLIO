import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PartieCreateComponent } from './partie/partie-create/partie-create.component';
import { PartieDetailComponent } from './partie/partie-detail/partie-detail.component';
import { AccueilComponent } from './accueil/accueil.component';
import { PartieListComponent } from './partie/partie-list/partie-list.component';
import { PartieMancheComponent } from './partie/partie-manche/partie-manche.component';

@NgModule({
  declarations: [
    AppComponent,
    PartieCreateComponent,
    PartieDetailComponent,
    AccueilComponent,
    PartieListComponent,
    PartieMancheComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
