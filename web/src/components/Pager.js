import React from "react";
import { Question } from "./Question";



export const Pager= ({questions,loading,hasErrors})=>{

 
 
 
 
 
 
 
    let questionsToRender=questions;
 
    const renderQuestions = () => {
        if (loading) return <p>Loading questions...</p>
        if (hasErrors) return <p>Unable to display questions.</p>

        return questionsToRender.map(question => <Question key={question.id} question={question} excerpt />)
    }

    return <>
        {renderQuestions()}
    </>
}



