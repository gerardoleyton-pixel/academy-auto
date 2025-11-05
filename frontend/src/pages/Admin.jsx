import React, { useEffect, useState } from 'react';
import api from '../api/axiosConfig';
import Modal from '../components/Modal.jsx';

const Admin = () => {
  const [courses, setCourses] = useState([]);
  const [modal, setModal] = useState({open:false,title:'',body:''});
  const [editing, setEditing] = useState(null);
  const [form, setForm] = useState({title:'', category:'', level:'BASIC'});

  const load = async () => {
    try {
      const res = await api.get('/courses');
      setCourses(res.data);
    } catch (e) { setModal({open:true,title:'Error',body:'No se pudieron cargar cursos.'}); }
  }

  useEffect(()=>{ load(); },[]);

  const save = async () => {
    try {
      if (editing) {
        await api.put('/courses/' + editing, form);
        setModal({open:true,title:'Actualizado',body:'Curso actualizado.'});
      } else {
        await api.post('/courses', form);
        setModal({open:true,title:'Creado',body:'Curso creado.'});
      }
      setForm({title:'',category:'',level:'BASIC'});
      setEditing(null);
      load();
    } catch (e) { setModal({open:true,title:'Error',body:'No se pudo guardar.'}); }
  }

  const remove = async (id) => {
    try { await api.delete('/courses/' + id); setModal({open:true,title:'Eliminado',body:'Curso eliminado.'}); load(); }
    catch (e) { setModal({open:true,title:'Error',body:'No se pudo eliminar.'}); }
  }

  const startEdit = (c) => { setEditing(c.id); setForm({title:c.title, category:c.category, level:c.level}); }

  return (
    <section className="admin">
      <h2>Administración - Cursos</h2>

      <div className="admin-form">
        <label>Título<input value={form.title} onChange={e=>setForm({...form,title:e.target.value})} /></label>
        <label>Categoría<input value={form.category} onChange={e=>setForm({...form,category:e.target.value})} /></label>
        <label>Nivel
          <select value={form.level} onChange={e=>setForm({...form,level:e.target.value})}>
            <option value="BASIC">Básico</option>
            <option value="INTERMEDIATE">Intermedio</option>
            <option value="ADVANCED">Avanzado</option>
          </select>
        </label>
        <div className="actions"><button className="btn" onClick={save}>{editing? 'Actualizar':'Crear'}</button></div>
      </div>

      <h3>Lista de cursos</h3>
      <div className="courses-grid">
        {courses.map(c=> (
          <article key={c.id} className="course-card">
            <h4>{c.title}</h4>
            <p>{c.category} — {c.level}</p>
            <div className="actions">
              <button className="btn" onClick={()=>startEdit(c)}>Editar</button>
              <button className="btn" onClick={()=>remove(c.id)}>Eliminar</button>
            </div>
          </article>
        ))}
      </div>

      {modal.open && <Modal title={modal.title} onClose={()=>setModal({open:false})}>{modal.body}</Modal>}
    </section>
  );
}

export default Admin;