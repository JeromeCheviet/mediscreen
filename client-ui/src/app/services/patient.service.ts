import {Injectable} from "@angular/core";
import {Patient} from "../models/patient.model";
import {map, Observable, switchMap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {NewPatient} from "../models/new-patient.model";

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  patients: Patient[] = [
    {
      patientId: 1,
      family: "TestNone",
      given: "Test",
      dob: "1966-12-31",
      sex: "F",
      address: "1 Brookside St",
      phone: "100-222-3333"
    },
    {
      patientId: 2,
      family: "TestBorderline",
      given: "Test",
      dob: "1945-06-24",
      sex: "M",
      address: "2 High St",
      phone: "200-333-4444"
    },
    {
      patientId: 3,
      family: "TestInDanger",
      given: "Test",
      dob: "2004-06-18",
      sex: "M",
      address: "3 Club Road",
      phone: "300-444-5555"
    },
    {
      patientId: 4,
      family: "TestEarlyOnset",
      given: "Test",
      dob: "2002-06-28",
      sex: "F",
      address: "4 Valley Dr",
      phone: "400-555-6666"
    }
  ];

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
    phone?: string }) {
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
  }): Observable<Patient> {
    const updatedPatient: Patient = {
      ...formValue
    };
    /*const indexPatient = this.patients.findIndex(patient => patient.patientId === updatedPatient.patientId);
    this.patients[indexPatient] = updatedPatient;*/
    this.http.put(`http://localhost:8081/patient/${updatedPatient.patientId}`, updatedPatient);
    return this.getPatientById(updatedPatient.patientId)
  }
}
