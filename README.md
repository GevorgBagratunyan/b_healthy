#B_Healthy

Short description:
It's a medical application, which prevents many dangerous situations associated with sudden changes in the state of human health 
by tracking patient's health status in realtime and immediately notifying attending doctors about dangerous situations. 
Facilitates the relationship between patients and doctors. Keeps and analyze all life medical history. 
Application let patients make an appointment with a doctor, etc.

Application features:
Obtain the patient's hemodynamic parameters such us "heart rate" and "saturation(oxygen in blood)" every minute,
analyze the collected parameters every 5 min, and if there are dangerous sudden changes or negative dynamics recorded,
the application immediately notifies all observing doctors. App can keep hemodynamic parameters for the last 7 days. 
There is also a temporary List of hemodynamic parameters, which keeps parameters of last 5 min, and which is cleared 
if dangerous changes were not recorded.

- Service for registering Users with two-factor authentication.
- Service for real-time tracking patient's health condition, and alerting.
- Service for sending notifications via SMS and Email.
- Service for making an appointment with the doctor.
- Service for viewing, sharing and analyzing patient's medical history

- Admin capabilities:
1) Add/delete/modify users(doctors and patients) and their data on Database.
In other words, the Admin has full control over all data.
2) Set/change permission rules to the endpoints for all users.

- Doctor capabilities: 
1) Accept or decline appointment requests from patients. 
2) Add/remove a patient to the own list of observed patients
3) In real time monitor his patients' health status. 
4) Receive instant notifications (via SMS and eMail) 
about sudden changes and dangerous situations associated 
with patients observed by him.

- Patient(as User) capabilities:
1) Send request to the doctor for an appointment.
2) Cancel accepted appointment(s)
3) Notify the doctor manually using his medical bracelet.
4) Retrieve all information from his medical history like appointments, exams, treatments, etc.


