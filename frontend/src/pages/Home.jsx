import React, {useEffect, useState} from 'react';
import {Button, Col, Container, Row} from "react-bootstrap";
import {Link, useOutletContext} from "react-router-dom";
import backend_api from "../api/backend_api.js";
import CustomSpinner from "../components/elements/CustomSpinner.jsx";

function Home() {
    const {categories, setCategories, setCurrentSubject} = useOutletContext();

    const [showAll, setShowAll] = useState(false);
    const displayedCategories = showAll ? categories : categories.slice(0, 20);

    const [loading, setLoading] = useState(true);

    const loadAllCategories = async () => {
        const response = await backend_api.get("/categories");
        const allCategories = response.data.categories.map(category => category.name);

        setCategories(allCategories);
        setLoading(false);
    }

    useEffect(() => {
        loadAllCategories();
    }, []);

    if (loading) {
        return <CustomSpinner/>
    }

    return (
        <Container className="mt-4">
            <Row>
                {displayedCategories.map((category, index) => (
                    <Col xs={6} md={3} className="mb-4" key={index}>
                        <Link to={`/books/${category}`}
                              className="btn btn-outline-light w-100 p-3"
                              onClick={() => {
                                  setCurrentSubject(category)
                              }}>
                            {category.charAt(0).toUpperCase() + category.slice(1)}
                        </Link>
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

export default Home;