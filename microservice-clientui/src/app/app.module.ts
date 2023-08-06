import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {HomeComponent} from './home/home.component';
import {AppRoutingModule} from "./app-routing.module";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatTableModule} from "@angular/material/table";
import { PatientFileComponent } from './patient-file/patient-file.component';
import {NgOptimizedImage} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import { AddPatientComponent } from './add-patient/add-patient.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    PatientFileComponent,
    AddPatientComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        MatTableModule,
        BrowserAnimationsModule,
        NgOptimizedImage,
        ReactiveFormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
