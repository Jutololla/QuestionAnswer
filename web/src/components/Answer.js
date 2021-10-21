import React from 'react'
import Button from 'emerald-ui/lib/Button';
import swal from 'sweetalert'
import { deleteAnswer } from '../actions/questionActions';

export const Answer = ({ answer, uid }) => {

  const onDelete = async (id) => {
    const responseDelete = await swal({
      title: "Do you really want to delete this ?",
      text: "Confirm if you want to delete this question",
      icon: "warning",
      buttons: ["Cancel", "Confirm"]
    })
    if (!responseDelete) {
      return
    }
    await deleteAnswer(id)
    swal({
      text: "The answer has been deleted successfully",
      icon: "success"
    })
  }






return <aside className="answer">
  <p>{answer.answer}</p>
  {uid === answer.userId && <Button
    key={answer.id}
    onClick={() => onDelete(answer.id)}
  >Delete</Button>}


</aside>

  }