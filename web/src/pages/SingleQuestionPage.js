import React, { useEffect } from 'react'
import { connect } from 'react-redux'

import { deleteAnswer, fetchQuestion } from '../actions/questionActions'

import { Question } from '../components/Question'
import { Answer } from '../components/Answer'
import { Link } from 'react-router-dom'
import swal from 'sweetalert'

const SingleQuestionPage = ({
  match,
  dispatch,
  question,
  hasErrors,
  loading,
  userId
}) => {
  const { id } = match.params
  useEffect(() => {
    dispatch(fetchQuestion(id))
  }, [dispatch, id, question])

  const onDelete = (id) => {
    swal({
      title: "Do you really want to delete this ?",
      text: "Confirm if you want to delete this question",
      icon: "warning",
      buttons: ["Cancel", "Confirm"]
    }).then((confirmed) => {
      if (confirmed) {
        dispatch(deleteAnswer(id))
        swal({
          text: "The answer has been deleted successfully",
          icon: "success"
        })
      }
    })
  }

  const renderQuestion = () => {
    if (loading.question) return <p>Loading question...</p>
    if (hasErrors.question) return <p>Unable to display question.</p>

    return <Question question={question} />
  }

  const renderAnswers = () => {
    return (question.answers && question.answers.length) ? question.answers.map(answer => (
      <Answer key={answer.id} answer={answer} uid={userId} onDelete={onDelete} />
    )) : <p>Empty answer!</p>;
  }

  return (
    <section>
      {renderQuestion()}
      {userId && <Link to={"/answer/" + id} className="button right">
        Reply
      </Link>}

      <h2>Answers</h2>
      {renderAnswers()}
    </section>
  )
}

const mapStateToProps = state => ({
  question: state.question.question,
  loading: state.question.loading,
  hasErrors: state.question.hasErrors,
  userId: state.auth.uid
})

export default connect(mapStateToProps)(SingleQuestionPage)
