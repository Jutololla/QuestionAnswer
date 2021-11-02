import React from 'react'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
  Link
} from 'react-router-dom'
import "firebase/firestore";
import "firebase/auth";
import { login, logout } from './actions/authActions';
import { PublicNavbar, PrivateNavbar } from './components/Navbar'
import HomePage from './pages/HomePage'
import SingleQuestionPage from './pages/SingleQuestionPage'
import QuestionsPage from './pages/QuestionsPage'
import QuestionFormPage from './pages/QuestionFormPage'
import AnswerFormPage from './pages/AnswerFormPage'
import OwnerQuestionsPage from './pages/OwnerQuestionsPage'
import QuestionEditFormPage from './pages/QuestionEditFormPage';
import { useAuthState } from "react-firebase-hooks/auth";
import Login from './pages/Login';
import { auth, GoogleProvider } from './services/FireBaseService';
import Register from './pages/Register';

const App = ({ dispatch }) => {
  const [user] = useAuthState(auth);
  if(user){
    dispatch(login(user.email, user.uid))
  }
  return (
    <Router>
      {user ?
        <>
          <PrivateNavbar />
          <Switch>
            <Route exact path="/" component={() => {
              return <HomePage><SignOut dispatch={dispatch} /></HomePage>
            }} />
            <Route exact path="/questions" component={QuestionsPage} />
            <Route exact path="/question/:id" component={SingleQuestionPage }  />
            <Route exact path="/list" component={OwnerQuestionsPage} />
            <Route exact path="/answer/:id" component={AnswerFormPage} />
            <Route exact path="/questionedit/:id" component={QuestionEditFormPage} />
            <Route exact path="/new" component={QuestionFormPage} />
            <Redirect to="/" />
          </Switch>
        </> :
        <>
          <PublicNavbar />
          <Switch>
            <Route exact path="/" component={() => {
              return <HomePage><SignIn dispatch={dispatch} /></HomePage>
            }} />
            <Route exact path="/questions" component={QuestionsPage} />
            <Route exact path="/question/:id" component={SingleQuestionPage} />
            <Route exact path="/answer/:id" component={AnswerFormPage} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/register" component={Register} />
            <Redirect to="/" />
          </Switch>
        </>
      }
      
    </Router>
  )
}


function SignIn() {
  const signInWithGoogle = () => {
    const provider = GoogleProvider();
    auth.signInWithPopup(provider);
  };
  return <>
  <button className="button right" onClick={signInWithGoogle}>Sign in with google</button>
  <Link to="/login" className="button right mx-2" >Sign in</Link >
  <Link to="/register" className="button right mx-2">Register</Link>
    
</>
}

function SignOut({ dispatch }) {
  return (
    auth.currentUser && (
      <>
      <button
        className="button right"
        onClick={() => {
          dispatch(logout())
          auth.signOut();
        }}
      >
        Sign out
      </button>
      
      </>
    )
  );
}


export default App
