import React, {useState} from 'react';
import {Button, Col, Container, Row} from "react-bootstrap";
import {Link, useOutletContext} from "react-router-dom";

function Home() {
    const {categories, setCurrentSubject} = useOutletContext();

    const [showAll, setShowAll] = useState(false);
    const displayedCategories = showAll ? categories : categories.slice(0, 20);

    return (
        <Container className="mt-4">
            <Row>
                {displayedCategories.map((category, index) => (
                    <Col xs={6} md={3} className="mb-4" key={index}>
                        <Link to={`/books/${category.toLowerCase()}`}
                                className="btn btn-outline-light w-100 p-3"
                                onClick={() => {setCurrentSubject(category)}}>{category}
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