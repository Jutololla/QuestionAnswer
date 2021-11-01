import React, { useEffect } from 'react'
import { connect } from 'react-redux'
import { Pager } from '../components/Pager'
import { fetchQuestions } from '../actions/questionActions'

const QuestionsPage = ({ dispatch, loading, questions, hasErrors }) => {
    useEffect(() => {
        dispatch(fetchQuestions())
    }, [dispatch])
    
    return (
        <section>
            <h1>Questions</h1>
            <Pager questions={questions}/>
        </section>
    )
}

const mapStateToProps = state => ({
    loading: state.question.loading,
    questions: state.question.questions,
    hasErrors: state.question.hasErrors,
})

export default connect(mapStateToProps)(QuestionsPage)
