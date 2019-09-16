'use strict';

import React from 'react';
import UpdateForm from './UpdateForm';
import UpdateHead from './UpdateHead';

class UpdatePage extends React.Component {
    render() {
        const handleUpdate = payload => this.props.actions.updateTodo(payload);
        const handleComplete = index => this.props.actions.completeTodo(index);
        return (
            <div>
                <UpdateHead/>
                <UpdateForm id={this.props.params.index} handleUpdate={handleUpdate} handleComplete={handleComplete} handleTodoLink={this.props.handleTodoLink} />
            </div>
            );
    }
}

export default UpdatePage;
