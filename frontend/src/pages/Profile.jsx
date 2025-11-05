import React, { useState, useEffect } from 'react';
import api from '../api/axiosConfig';
import Modal from '../components/Modal.jsx';

const Profile = ({ user, setUser, onNavigate }) => {
  const [modal, setModal] = useState({open:false, title:'', body:''});
  const [courses, setCourses] = useState([]);

  useEffect(() => {
    (async () => {
      try {
        const res = await api.get('/courses');
        setCourses(res.data);
      } catch (e) {
        setModal({open:true, title:'Error', body:'No se pudieron cargar cursos'});
      }
    })();
  }, []);

  const deleteProfile = async () => {
    if (!user) return;
    try {
      await api.delete('/users/' + user.id);
      localStorage.removeItem('academy_user');
      setUser(null);
      onNavigate('home');
    } catch (e) {
      setModal({open:true, title:'Error', body:'No se pudo eliminar el perfil.'});
    }
  }

  const enroll = async (courseId) => {
    if (!user) { setModal({open:true,title:'Atención',body:'Debes iniciar sesión para matricularte.'}); return; }
    try {
      const res = await api.post('/enrollments', null, { params: { userId: user.id, courseId } });
      setModal({open:true, title:'Matriculado', body:`Matriculado en ${res.data.course.title}`});
    } catch (e) {
      setModal({open:true, title:'Error', body:'No fue posible matricularse.'});
    }
  }

  return (
    <section className="profile">
      <h2>Perfil de {user?.username}</h2>
      <div>
        <strong>Role:</strong> {user?.role}
      </div>

      <div className="profile-actions">
        <button className="btn" onClick={deleteProfile}>Eliminar mi perfil</button>
      </div>

      <h3>Cursos disponibles</h3>
      <div className="courses-grid">
        {courses.map(c => (
          <article key={c.id} className="course-card">
            <h4>{c.title}</h4>
            <p>{c.category} — {c.level}</p>
            <div className="actions">
              <button className="btn" onClick={() => enroll(c.id)}>Matricular</button>
            </div>
          </article>
        ))}
      </div>

      {modal.open && <Modal title={modal.title} onClose={()=>setModal({open:false})}>{modal.body}</Modal>}
    </section>
  );
}

export default Profile;