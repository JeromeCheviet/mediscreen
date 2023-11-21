import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {PatientFileComponent} from "./patient-file/patient-file.component";
import {AddPatientComponent} from "./add-patient/add-patient.component";
import {UpdatePatientComponent} from "./update-patient/update-patient.component";
import {AddNoteComponent} from "./add-note/add-note.component";
import {UpdateNoteComponent} from "./update-note/update-note.component";
import {PatientAssessComponent} from "./patient-assess/patient-assess.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'patient/:id', component: PatientFileComponent},
  {path: 'addPatient', component: AddPatientComponent},
  {path: 'updatePatient/:id', component: UpdatePatientComponent},
  {path: 'addNote/:id', component: AddNoteComponent},
  {path: 'updateNote/:id', component: UpdateNoteComponent},
  {path: 'assess/:id', component: PatientAssessComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
