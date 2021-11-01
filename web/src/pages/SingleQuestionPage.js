import React, { useEffect } from 'react'
import { connect } from 'react-redux'
import { deleteAnswer, deleteVote, fetchQuestion } from '../actions/questionActions'
import { plusAnswerVote } from '../actions/questionActions'
import { subtractAnswerVote } from '../actions/questionActions'
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
  userId,
  history
}) => {
  const { id } = match.params
  
  useEffect(() => {
    dispatch(fetchQuestion(id))
  }, [dispatch, id, question,question.answer])

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

  const onEdit = () => {
    (question.answers && question.answers.length) &&
      swal({
        title: "Editing this question will create a new copy of it, because it already has answers",
        text: "Confirm if you want clone this question",
        icon: "warning",
        buttons: ["Cancel", "Confirm"]
      })
      .then((response)=>{        
        if(response){
          return history.push('/new/');
        }
      })

  }

  const onPlus = (answer) =>{
    console.log(answer)
    deleteVoteByUserIdInQuestion(answer)
    dispatch(plusAnswerVote(answer))
  
  }

  const onSubtract = (answer) =>{
    console.log(answer)
    deleteVoteByUserIdInQuestion(answer)
    dispatch(subtractAnswerVote(answer))
  }

  const deleteVoteByUserIdInQuestion=(answer)=>{
    dispatch(deleteVote(answer))
  }

  
  

  const renderQuestion = () => {
    if (loading.question) return <p>Loading question...</p>
    if (hasErrors.question) return <p>Unable to display question.</p>

    return <>
      <Question question={question} />
    </>
  }

  const renderAnswers = () => {
    return (question.answers && question.answers.length) ? question.answers
    .sort(function(a, b){return b.position-a.position})
    
    .map(answer => (
      <Answer key={answer.id} answer={answer} uid={userId} onDelete={onDelete}
      onPlus={onPlus} onSustract={onSubtract} />
    )) : <p>Empty answer!</p>;
  }

  return (
    <section>
      {renderQuestion()}
      {userId && <div><Link to={"/answer/" + id} className="button right">
        Reply
      </Link>
      {question.userId===userId&&<div>
        {
        (question.answers && question.answers.length) ? 
        <button className="button right" onClick={onEdit}>Edit</button>
          : <Link to={"/questionEdit/" + id} className="button right">Edit</Link>
        }
        </div>
}
          
          
      
      </div>}

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
