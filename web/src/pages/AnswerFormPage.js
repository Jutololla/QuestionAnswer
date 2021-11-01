import React, { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import {  fetchQuestion, postAnswer, sendMail } from '../actions/questionActions'
import { connect } from 'react-redux'
import { Question } from '../components/Question'
import { Input } from "../components/Input";

const FormPage = ({ dispatch, loading, redirect, match,hasErrors, question, userId, userEmail }) => {
    const [content, setContent]=useState('');
    const { id } = match.params
    const history = useHistory();
    const mail =({});
    
 const validateInput = ({answer}) => {
        if(answer.length && answer.length <=1000) {
            return true;
        }
        return false;
    }

    const onSubmit = e => {
        e.preventDefault();

        const data = {
            userId,
            questionId:id,
            answer:content
        }
        if(validateInput(data)){
            dispatch(postAnswer(data));
            prepareMail({userEmail,question,mail,data});
        } 
    }  
        
    const prepareMail = ({userEmail,question,mail,data}) =>{
        mail.toEmail=userEmail;
        mail.toName="defaultUserName";
        mail.question=question.question;
        console.log({mail})
       dispatch(sendMail(mail,data));
    }

    useEffect(() => {
        dispatch(fetchQuestion(id))
    }, [dispatch, id])

    useEffect(() => {
        if (redirect) {
            history.push(redirect);
        }
    }, [redirect, history])

    const renderQuestion = () => {
        if (loading.question) return <p>Loading question...</p>
        if (hasErrors.question) return <p>Unable to display question.</p>

        return <Question question={question} />
    }

    return (
        <section>
            {renderQuestion()}
            <h1>New Answer</h1>

            <form question={question} 
            userEmail={userEmail}
            mail={mail}
            onSubmit={onSubmit}>
                <div>
                   <Input id="answer" setContent={setContent}/>

                   </div>
                <button type="submit" className="button" disabled={loading} >{
                    loading ? "Saving ...." : "Save"
                }</button>
            </form>
        </section>

    );
}

const mapStateToProps = state => ({
    loading: state.question.loading,
    redirect: state.question.redirect,
    question: state.question.question,
    hasErrors: state.question.hasErrors,
    userId: state.auth.uid,
    userEmail: state.auth.email,
    pathname:state.pathname
})

export default connect(mapStateToProps)(FormPage)