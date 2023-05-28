# lab02 feedback for LUA ZHI ZHAN
### TEST buttons "TEMPERATURE CONVERTER", "CURRENCY CONVERTER" & "EMAIL FRIEND" exist; buttons can be clicked and respective activity launched
- test PASSED.  
### TEST email preview works for inputs: "mememe", "me@you.com" & "xinjiapuo"
- test PASSED.  
### TEST currency converter outputs "70" for inputs: "USD", "0.7", "100"
- test PASSED.  
### TEST existence of headerTextView, destinationScrollView
- test PASSED.  
### TEST temperature converter outputs "31.1xx" for inputs: "88", "to Celsius"
- test PASSED.  
### TEST temperature converter outputs "51.8" for inputs: "11", "to Fahrenheit"
- FAILED Reason-> No views in hierarchy found matching: an instance of android.widget.TextView and view.getText() with or without transformation to match: a string containing "51.8"... (err msg too long)  
### TEST "SEND EMAIL" button opens some email app
- FAILED Reason-> Error performing 'single click - At Coordinates: 539, 1224 and precision: 16, 16' on view '(view.getClass().getName() matches: a string ending with "Button" and an instance of android.widget.TextView and view.getText() with or without transformation to match: equalToIgnoringCase("SEND EMAIL"))'.... (err msg too long)  
### TEST "CLEAR" button works in currency converter
- test PASSED.  

** Your code passed more than 6/8 of the test cases. **  

### Your final score for lab02 is 1 out of 1.