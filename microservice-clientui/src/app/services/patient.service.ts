import {Injectable} from "@angular/core";
import {Patient} from "../models/patient.model";

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

  getAllPatients(): Patient[] {
    return this.patients;
  }

  getPatientById(Id: number): Patient {
    const patient = this.patients.find(patient => patient.patientId === Id);
    if (!patient) {
      throw new Error('Patient not found');
    } else {
      return patient;
    }
  }
}
