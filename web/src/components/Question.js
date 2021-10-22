import React from 'react'
import { Link } from 'react-router-dom'

export const Question = ({ question, excerpt, onDelete}) => (
  <article className={excerpt ? 'question-excerpt' : 'question'}>
    <div dangerouslySetInnerHTML={{__html:question.question}} />
    <p>{question.category}  - <small>{question.type}</small></p>
   

    
    {onDelete && (
      <button className="button right" onClick={() => onDelete(question.id)}>DELETE</button>
    )}
    {excerpt && (
      <Link to={`/question/${question.id}`} className="button">
        View Question
      </Link>
    )}
  </article>
)
