import React, { useEffect } from 'react'
import { connect } from 'react-redux'
import swal from 'sweetalert'
import { fetchOwnerQuestions, deleteQuestion } from '../actions/questionActions'
import { Pager } from '../components/Pager'


const OwnerQuestionsPage = ({ dispatch, loading, questions, hasErrors, redirect, userId }) => {
    useEffect(() => {
        dispatch(fetchOwnerQuestions(userId))
    }, [dispatch, userId]);

    useEffect(() => {
        if (redirect) {
            dispatch(fetchOwnerQuestions(userId))
        }
    }, [redirect, dispatch, userId]);

    const onDelete = (id) => {
        swal({
            title:"Do you really want to delete this ?",
            text: "Confirm if you want to delete this question",
            icon: "warning",
            buttons:["Cancel", "Confirm"]
        }).then(responseDelete=>{
            if(responseDelete){
            dispatch(deleteQuestion(id))
            swal({
                text:"The answer has been deleted successfully",
                icon:"success"
            })}
        });
        
    }

    const renderQuestions = () => {
        if (loading) return <p>Loading questions...</p>
        if (hasErrors) return <p>Unable to display questions.</p>

        return <Pager questions={questions} excerpt onDelete={onDelete}></Pager>
    }

    return (
        <section>
            <h1>Questions</h1>
            {renderQuestions()}
        </section>
    )
}

const mapStateToProps = state => ({
    loading: state.question.loading,
    questions: state.question.questions,
    hasErrors: state.question.hasErrors,
    redirect: state.question.redirect,
    userId: state.auth.uid
})

export default connect(mapStateToProps)(OwnerQuestionsPage)
