import firebase from "firebase/app";
import "firebase/firestore";
import "firebase/auth";


firebase.initializeApp({
    apiKey: "AIzaSyBes3j4uHIOmwpWz_YdCBb04i_TpDOoHM0",
    authDomain: "questionanswer-e95e5.firebaseapp.com",
    projectId: "questionanswer-e95e5",
    storageBucket: "questionanswer-e95e5.appspot.com",
    messagingSenderId: "922604461550",
    appId: "1:922604461550:web:39037df914c710a73f02ff",
    measurementId: "G-LJC5TFQ4HE"
  });
  
  export const auth = firebase.auth();
  export const GoogleProvider = () => new firebase.auth.GoogleAuthProvider();