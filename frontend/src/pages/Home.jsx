import React, { useEffect, useState } from 'react';
import api from '../api/axiosConfig';
import Modal from '../components/Modal.jsx';

const Home = ({ onNavigate }) => {
  const [courses, setCourses] = useState([]);
  const [modal, setModal] = useState({ open:false, title:'', body:'' });

  useEffect(() => {
    (async () => {
      try {
        const res = await api.get('/courses');
        setCourses(res.data);
      } catch (e) {
        setModal({ open:true, title:'Error', body:'No se pudieron cargar los cursos.' });
      }
    })();
  }, []);

  return (
    <section className="home">
      <h1>Nuestros Cursos</h1>
      <p>Ofrecemos cursos de idiomas y tecnología: Inglés, Francés, Italiano, Excel, IA y Python.</p>
      <div className="courses-grid">
        {courses.length === 0 && <div className="placeholder">No hay cursos cargados — regístrate para matricularte</div>}
        {courses.map(c => (
          <article key={c.id} className="course-card">
            <h3>{c.title}</h3>
            <p>{c.category} — {c.level}</p>
            <div className="actions">
              <button className="btn" onClick={() => onNavigate('auth')}>Matricularme</button>
            </div>
          </article>
        ))}
      </div>

      {modal.open && <Modal title={modal.title} onClose={() => setModal({open:false})}>{modal.body}</Modal>}
    </section>
  );
}

export default Home;