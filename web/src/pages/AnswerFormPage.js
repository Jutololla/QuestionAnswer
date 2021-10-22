import React, { useEffect } from "react";
import { useForm } from "react-hook-form";
import { useHistory } from "react-router-dom";
import {  fetchQuestion, postAnswer, sendMail } from '../actions/questionActions'
import { connect } from 'react-redux'
import { Question } from '../components/Question'

const FormPage = ({ dispatch, loading, redirect, match,hasErrors, question, userId, userEmail }) => {
    const { register, handleSubmit } = useForm();
    const { id } = match.params
    const history = useHistory();
    const mail =({});
    

    const onSubmit = data => {
        data.userId =  userId;
        data.questionId = id;
        dispatch(postAnswer(data));
        prepareMail({userEmail,question,mail});
    };
        
    const prepareMail = ({userEmail,question,mail}) =>{
        mail.toEmail=userEmail;
        mail.toName="defaultUserName";
        mail.question=question.question;
        console.log({mail})
       dispatch(sendMail(mail));
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
            onSubmit={handleSubmit(onSubmit)}>
                <div>
                    <label for="answer">Answer</label>
                    <textarea id="answer" {...register("answer", { required: true, maxLength: 300 })} />
                </div>
                <button type="submit" className="button" disabled={loading} >{
                    loading ? "Saving ...." : "Saved"
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
    userEmail: state.auth.email
})

export default connect(mapStateToProps)(FormPage)