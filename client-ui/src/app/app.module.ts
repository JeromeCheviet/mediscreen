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
import { UpdatePatientComponent } from './update-patient/update-patient.component';
import {HttpClientModule} from "@angular/common/http";
import { PatientNoteComponent } from './patient-note/patient-note.component';
import { AddNoteComponent } from './add-note/add-note.component';
import { UpdateNoteComponent } from './update-note/update-note.component';
import { PatientAssessComponent } from './patient-assess/patient-assess.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    PatientFileComponent,
    AddPatientComponent,
    UpdatePatientComponent,
    PatientNoteComponent,
    AddNoteComponent,
    UpdateNoteComponent,
    PatientAssessComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        MatTableModule,
        BrowserAnimationsModule,
        NgOptimizedImage,
        ReactiveFormsModule,
        HttpClientModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
