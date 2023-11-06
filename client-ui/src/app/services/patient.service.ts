import {Injectable} from "@angular/core";
import {Patient} from "../models/patient.model";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {NewPatient} from "../models/new-patient.model";

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private http: HttpClient) {
  }

  getAllPatients(): Observable<Patient[]> {
    return this.http.get<Patient[]>('http://localhost:8081/patient')
  }

  getPatientById(patientId: number): Observable<Patient> {
    return this.http.get<Patient>(`http://localhost:8081/patient/${patientId}`)
  }

  addPatient(formValue: {
    family: string,
    given: string,
    dob: string,
    sex: string,
    address?: string,
    phone?: string
  }) {
    const newPatient: NewPatient = {
      ...formValue
    };
    this.http.post<NewPatient>('http://localhost:8081/patient/add', newPatient).subscribe();
  }

  updatePatient(formValue: {
    patientId: number,
    family: string,
    given: string,
    dob: string,
    sex: string,
    address?: string,
    phone?: string
  }): void {
    const updatedPatient: Patient = {
      ...formValue
    };
    console.log(updatedPatient.family)
    console.log(updatedPatient.patientId)
    this.http.put<Patient>(`http://localhost:8081/patient/${updatedPatient.patientId}`, updatedPatient).subscribe();
  }
}
