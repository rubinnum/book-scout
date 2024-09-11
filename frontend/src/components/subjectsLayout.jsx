import React, {useState} from 'react';
import backend_api from "../api/backend_api.js";
import {Button, Col, Container, Row} from "react-bootstrap";

function SubjectsLayout({categories, setCurrentSubject, setBooks}) {
    const [showAll, setShowAll] = useState(false);
    const displayedCategories = showAll ? categories : categories.slice(0, 20);

    const getBooksBySubject = async (subject) => {
        const books = await backend_api.get(`/books/${subject}`);
        setBooks(books);
        setCurrentSubject(subject);
    }

    return (
        <Container className="mt-4">
            <Row>
                {displayedCategories.map((category, index) => (
                    <Col xs={6} md={3} className="mb-4" key={index}>
                        <Button variant="outline-light" className="w-100 p-3" onClick={() => {getBooksBySubject(category)}}>{category}</Button>
                    </Col>
                ))}
            </Row>

            <div className="text-center">
                {
                    (
                        <Button variant="light" className="mb-4" onClick={() => setShowAll(!showAll)}>
                            {showAll ? "Show Less" : "Show More"}
                        </Button>
                    )
                }

            </div>
        </Container>
    );
}

export default SubjectsLayout;