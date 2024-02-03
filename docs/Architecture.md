# ARCHITECTURE

##  3-tier architecture.

### Domain Specific Object (DSO) 
Objects that represent data that is passed around between the layers.<br> 
**- UserCredentials:** Consists of the  username and password used during the login process to verify and authenticate a user's identity.<br>
**- UserDetails:** Includes information like name, age, height and contact details, and any other relevant personal data.<br>
**- MoodLog:** Corresponds to a single day's input from the mood tracker such as mood rating, hours of sleep, and possibly additional notes.<br>
**- SymptomLog:** Corresponds to a single day's input from the Symptoms Tracker such as mental and physical health symptoms.<br>
**- MedicationLog:** Corresponds to a single day's record of a user's medication usage from the Medication Tracker.<br>
**- SubstanceUseLog:** Corresponds to a single day's input from the Substance Use Tracker such as the consumption of substances like alcohol, tobacco, or other substances.<br>

### Presentation Layer
Responsible for handling the user interface (UI) and user experience (UX). It is the part of the system that users directly interact with, providing a means for them to input data, receive information, and interact with the application's features.<br>
**- LoginActivity:** Page that will allow user to login or to sign up either as a patient or as a doctor, and will retrieve user credentials for its verification.<br>
**- HomePageActivity:** Main screen of the app, providing users with an overview of the 4 different trackers and an option for user datails.<br>
**- UserDetails Activity:**  Provides the user interface for managing personal information.<br>
**- MoodTrackerActivity:** Allows users to input and visualize their mood. It would likely include graphical representations to show mood trends, an option to input the hrs of sleep and one for notes<br>
**- SymptomTrackerActivity:** Allows users to input and track the frequency of any symptoms they are experiencing. The app could possibly provide insights or trends.<br>
**- MedicationTrackerActivity:** Allows users to input and track their medication information, such as name of medications, doses or side-effects.<br>
**- SubstanceUseTrackerActivity:** Allows users to input how often they use any substances, allowing them to describe what you used and how you felt on those days.<br>

### Logic Layer
Responsible for implementing the business rules, processing data, and coordinating the flow of information between the user interface (presentation layer) and the data storage (persistence layer). The Logic Layer contains the core functionality and rules that define how the application operates.<br>
**- UserValidationHandler:** Verify user credentials, and provide access to the app's secured features upon successful authentication.<br>
**- UserDetailsHandler:**  Manages and retrieves user-related information.<br>
**- MoodTrackerHandler:** Handles the recording and retrieval of mood-related entries. May include logic for analyzing and presenting trends or insights based on mood data.<br>
**- SymptomTrackerHandler:** Manages the tracking and processing of user-reported symptoms. May include logic for analyzing and presenting trends or patterns in symptom data.<br>
**- MedicationTrackerHandler:** Manages the tracking and processing of user medication-related data.<br>
**- SubstanceUseTrackerHandler:** Manages the tracking and processing of user substance use-related data.<br>

### Data/Persistence Layer
Responsible for managing the storage and retrieval of data. The primary role of the Persistence Layer is to abstract the details of data storage, providing a clean and consistent interface for the Logic Layer to interact with data.<br> 
**- UserRegistrationPersistence:** Responsible for storing and retrieving information related to user registrations. It may include details such as usernames, passwords, email addresses, users details. Interfaces with the UserDetailsHandler  and UserValidationHandler from the Business Logic Layer, to ensure accurate and secure handling of user registration data.<br>
**- WellnessLogPersistence:** This component deals with storing and retrieving mood-related information logged by users. It interfaces with all four tracker handlers to organize and store each user's logs.<br>

## Iteration 1 Diagram

![architecture](architecture-1.png)
