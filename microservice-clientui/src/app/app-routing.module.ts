import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {PatientFileComponent} from "./patient-file/patient-file.component";
import {AddPatientComponent} from "./add-patient/add-patient.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'patient/:id', component: PatientFileComponent },
  { path: 'addPatient', component: AddPatientComponent }
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {}
