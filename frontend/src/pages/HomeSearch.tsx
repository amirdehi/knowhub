import React, { useState } from 'react'

type Experience = { id:number; title:string; body:string }
type Question = { id:number; title:string; body:string }
type Answer = { id:number; body:string; question?: Question }

export default function HomeSearch(){
  const [q,setQ] = useState('')
  const [res,setRes] = useState<any>(null)
  async function doSearch(e:React.FormEvent){
    e.preventDefault()
    const r = await fetch(`/api/search?q=${encodeURIComponent(q)}`)
    const j = await r.json()
    setRes(j)
  }
  return (
    <div style={{minHeight:'100vh', display:'grid', placeItems:'center'}}>
      <div style={{width:'min(800px, 95vw)'}}>
        <form onSubmit={doSearch}>
          <input value={q} onChange={e=>setQ(e.target.value)} placeholder="Search experiences, questions, answers..." style={{width:'100%', padding:'16px', borderRadius:'12px', border:'1px solid #ddd'}} />
        </form>
        {res && (
          <div style={{display:'grid', gridTemplateColumns:'repeat(3, 1fr)', gap:'16px', marginTop:'24px'}}>
            <Group title="Experiences" items={res.experiences} render={(e:Experience)=>(
              <Card key={e.id} title={e.title} snippet={e.body}/>
            )}/>
            <Group title="Questions" items={res.questions} render={(q:Question)=>(
              <Card key={q.id} title={q.title} snippet={q.body}/>
            )}/>
            <Group title="Answers" items={res.answers} render={(a:Answer)=>(
              <Card key={a.id} title={`Answer #${a.id}`} snippet={a.body}/>
            )}/>
          </div>
        )}
      </div>
    </div>
  )
}

function Group({title, items, render}:{title:string, items:any[], render:(x:any)=>React.ReactNode}){
  return (
    <div>
      <h3>{title}</h3>
      <div style={{display:'grid', gap:'8px'}}>
        {items?.map(render)}
      </div>
    </div>
  )
}

function Card({title, snippet}:{title:string, snippet:string}){
  return (
    <div style={{border:'1px solid #eee', borderRadius:'12px', padding:'12px'}}>
      <div style={{fontWeight:600}}>{title}</div>
      <div style={{fontSize:'0.9rem', color:'#555'}}>{(snippet||'').slice(0,180)}...</div>
    </div>
  )
}
