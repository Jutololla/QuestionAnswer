import React from "react";
import { Question } from "./Question";
import { useEffect } from "react";



export const Pager= ({questions,loading,hasErrors})=>{

    useEffect(() => {
        paginatorProps=paginator(questions,currentPage,10)
        renderQuestions(paginatorProps.data)
        console.log("useEffect")
      }, [currentPage])

    function paginator(items, current_page, per_page_items) {
        let page = current_page || 1,
        per_page = per_page_items || 10,
        offset = (page - 1) * per_page,
    
        paginatedItems = items.slice(offset).slice(0, per_page_items),
        total_pages = Math.ceil(items.length / per_page);
    
        return {
            page: page,
            per_page: per_page,
            pre_page: page - 1 ? page - 1 : null,
            next_page: (total_pages > page) ? page + 1 : null,
            total: items.length,
            total_pages: total_pages,
            data: paginatedItems
        };
    }

    let currentPage=1;
    let paginatorProps=paginator(questions,currentPage,10)


    const prevPage=()=>{
        currentPage=currentPage-1;
        console.log(currentPage)

    }
 
    const nextPage=()=>{
        currentPage=currentPage+1;
        console.log(currentPage)

    }
 
    const renderQuestions = (data) => {
        if (loading) return <p>Loading questions...</p>
        if (hasErrors) return <p>Unable to display questions.</p>

        return data.map(question => <Question key={question.id} question={question} excerpt />)
    }

    return (<>
    {renderQuestions(paginatorProps.data)}
    {paginatorProps.pre_page&&<button onClick={prevPage}>{paginatorProps.pre_page}</button>}
    <button disabled={true}>{paginatorProps.page}</button>
    {paginatorProps.next_page&&<button onClick={nextPage}>{paginatorProps.next_page}</button>}
    </>
    )
}



