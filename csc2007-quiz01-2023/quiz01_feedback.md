# quiz01 feedback for LUA ZHI ZHAN
### 1. RegistrationActivity layout and UI elements are similar to screenshot
- UI has imageViewVaccination, editTextSMSCode, editTextNRIC, buttonGetStarted and buttonClear in view
- PASSED.  
### 2. MainActivity layout and UI elements are similar to screenshot
- type in FJR52F5ID8/S8039624D and click on buttonGetStarted
- UI has textViewWelcome, editTextDate spinnerPolyClinic and textViewAddress elements in view
- PASSED.  
### 3. ComfirmActivity layout and UI elements are similar to screenshot
- type in FJR52F5ID8/S8039624D and click on buttonGetStarted
- click on buttonBookAppointment
- UI has textViewNRIC, textViewCentre textViewCentreAddress and textViewBookingDate elements in view
- PASSED.  
### 4. Logic on the Clear button to clear the fields
- type in FJR52F5ID8/S8039624D and click buttonClear
- check textViewWelcome not displayed, and both FJR52F5ID8 and S8039624D are not displayed
- PASSED.  
### 5. Logic to check SMS registration code validity: Exactly 10 characters
- type in JR52F5ID8/S8039624D and click buttonGetStarted
- check textViewWelcome not displayed
- type in AFJR52F5ID8/S8039624D and click buttonGetStarted
- check textViewWelcome not displayed
- type in 1234567890/S8039624D and click buttonGetStarted
- check textViewWelcome not displayed
- type in ABCDEFGHIJ/S8039624D and click buttonGetStarted
- check textViewWelcome not displayed
- PASSED.
### 6. Logic to check NRIC validity: Check for exactly 9 chars total and exactly 7 numerical digits
- type in FJR52F5ID8/S333A and click buttonGetStarted
- check textViewWelcome not displayed
- type in FJR52F5ID8/S88888888A and click buttonGetStarted
- check textViewWelcome not displayed
- type in A1B2C3D4E5/SS123456A and click buttonGetStarted
- check textViewWelcome not displayed
- type in A1B2C3D4E5/S123456AA and click buttonGetStarted
- check textViewWelcome not displayed
- type in A1B2C3D4E5/S12S4567A and click buttonGetStarted
- check textViewWelcome not displayed
- type in A1B2C3D4E5/*1234567E and click buttonGetStarted
- check textViewWelcome not displayed
- type in A1B2C3D4E5/11234567S and click buttonGetStarted
- check textViewWelcome not displayed
- type in A1B2C3D4E5/S12345677 and click buttonGetStarted
- check textViewWelcome not displayed
- PASSED.  
### 7. Display error message if SMS registration code is invalid
- type in JR52F5ID8/S8039624D and click buttonGetStarted
- check textViewError with text "Please enter a valid code" displayed 
- PASSED.  
### 8. Display error message if NRIC is invalid
- type in A1B2C3D4E5/*1234567E and click buttonGetStarted
- check textViewError with text "Please enter a valid NRIC" displayed 
- PASSED.  
### 10. Display welcome message with NRIC entered from registration screen
- type in A1B2C3D4E5/S8039624D and click buttonGetStarted
- check textViewWelcome displayed and contains S8039624D
- PASSED.  
### 11. Implement the logic to display today's date in the text field in DD/MM/YYYY format
- type in A1B2C3D4E5/S8039624D and click buttonGetStarted
- check that the regex "[0-9]{2}/[0-9]{2}/[0-9]{4}" can be found in editTextDate
- PASSED.  
### 12. Implement the logic in the spinner to update the address based on the
selected vaccination centre
- type in A1B2C3D4E5/S8039624D and click buttonGetStarted
- choose JTVC Sengkang (Former Preschool) from spinnerPolyClinic
- check that 60 Sengkang East Way and S(548596) are contained in textViewAddress
- choose JTVC Jurong East (Former Shuqun Secondary School) from spinnerPolyClinic
- check that 450 Jurong East Street 21 and S(609604) are contained in textViewAddress
- PASSED.  
### 13. Navigate to confirmation screen based on valid booking
- type in A1B2C3D4E5/S8039624D and click buttonGetStarted
- choose JTVC Jurong East (Former Shuqun Secondary School) from spinnerPolyClinic
- click buttonBookAppointment
- check textViewCentre displayed
- PASSED.  
### 14. Implement the logic to display confirmation information
 based on selected vaccination centre, address, NRIC and date
 on the MainActivity
- type in A1B2C3D4E5/S8039624D and click buttonGetStarted
- choose JTVC Jurong East (Former Shuqun Secondary School) from spinnerPolyClinic
- click buttonBookAppointment
- check textViewNRIC, textViewCentre, textViewAddress, textViewBookingDate displayed
- PASSED.  
### 9. Navigate to successful main screen based on valid registration
- type in A1B2C3D4E5/S8039624D and click buttonGetStarted
- check textViewWelcome displayed
- PASSED.  

** Your functional testing score for quiz01 is 14/14. **  

### Note that there are still 2 non-func testing marks:
- Modular code design / naming conventions (1 mark)
- Well-commented code (1 mark)

** Your non-functional testing score for quiz01 is 2/2. **

** Your overall score for quiz01 is 16/16. ** 