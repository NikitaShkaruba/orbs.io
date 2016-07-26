'use strict';

const React = require('react');
const client = require('./client');
const follow = require('./follow'); // function to hop multiple links by "rel"
const when = require('when');
const root = '/api';

// Prime component which is the app itself. Handles all rest actions, inclues all other inside his render()
class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {employees: [], attributes: [], pageSize: 2, links: {}};

        // Bind tag action handlers with functions
        this.updatePageSize = this.updatePageSize.bind(this);
        this.onCreate = this.onCreate.bind(this);
        this.onUpdate = this.onUpdate.bind(this);
        this.onDelete = this.onDelete.bind(this);
        this.onNavigate = this.onNavigate.bind(this);
    }

    componentDidMount() {
        this.loadFromServer(this.state.pageSize);
    }

    // load employees from server
    loadFromServer(pageSize) {
        follow(client, root, [
            {rel: 'employees', params: {size: pageSize}}]
        ).then(employeeCollection => {
            return client({
                method: 'GET',
                path: employeeCollection.entity._links.profile.href,
                headers: {'Accept': 'application/schema+json'}
            }).then(schema => {
                this.schema = schema.entity;
                this.links = employeeCollection.entity._links;
                return employeeCollection;
            });
        }).then(employeeCollection => {
            return employeeCollection.entity._embedded.employees.map(employee =>
                client({
                    method: 'GET',
                    path: employee._links.self.href
                })
            );
        }).then(employeePromises => {
            return when.all(employeePromises);
        }).done(employees => {
            this.setState({
                employees: employees,
                attributes: Object.keys(this.schema.properties),
                pageSize: pageSize,
                links: this.links
            });
        });
    }

    // rest create action handler
    onCreate(newEmployee) {
        follow(client, root, ['employees']).then(employeeCollection => {
            return client({
                method: 'POST',
                path: employeeCollection.entity._links.self.href,
                entity: newEmployee,
                headers: {'Content-Type': 'application/json'}
            })
        }).then(response => {
            return follow(client, root, [
                {rel: 'employees', params: {'size': this.state.pageSize}}]);
        }).done(response => {
            this.onNavigate(response.entity._links.last.href);
        });
    }

    // rest update action handler
    onUpdate(employee, updatedEmployee) {
        client({
            method: 'PUT',
            path: employee.entity._links.self.href,
            entity: updatedEmployee,
            headers: {
                'Content-Type': 'application/json',
                'If-Match': employee.headers.Etag
            }
        }).done(response => {
            this.loadFromServer(this.state.pageSize);
        }, response => {
            if (response.status.code === 412) {
                alert('DENIED: Unable to update ' +
                    employee.entity._links.self.href + '. Your copy is stale.');
            }
        });
    }

    // rest delete action handler
    onDelete(employee) {
        client({method: 'DELETE', path: employee.entity._links.self.href}).done(response => {
            this.loadFromServer(this.state.pageSize);
        });
    }

    // navigation handler
    onNavigate(navUri) {
        client({
            method: 'GET',
            path: navUri
        }).then(employeeCollection => {
            this.links = employeeCollection.entity._links;

            return employeeCollection.entity._embedded.employees.map(employee =>
                client({
                    method: 'GET',
                    path: employee._links.self.href
                })
            );
        }).then(employeePromises => {
            return when.all(employeePromises);
        }).done(employees => {
            this.setState({
                employees: employees,
                attributes: Object.keys(this.schema.properties),
                pageSize: this.state.pageSize,
                links: this.links
            });
        });
    }


    updatePageSize(pageSize) {
        if (pageSize !== this.state.pageSize) {
            this.loadFromServer(pageSize);
        }
    }

    render() {
        return (
            <div>
                <h2>orbs.io</h2>
                <CreateDialog attributes={this.state.attributes} onCreate={this.onCreate}/>
                <EmployeeList employees={this.state.employees}
                              links={this.state.links}
                              pageSize={this.state.pageSize}
                              attributes={this.state.attributes}
                              onNavigate={this.onNavigate}
                              onUpdate={this.onUpdate}
                              onDelete={this.onDelete}
                              updatePageSize={this.updatePageSize}/>
            </div>
        )
    }
}

// Class for handling creation of new employee
class CreateDialog extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this); // why do we have to bind this? :\
    }

    handleSubmit(e) {
        e.preventDefault();
        var newEmployee = {};

        // get attributes from create form
        this.props.attributes.forEach(attribute => {
            newEmployee[attribute] = React.findDOMNode(this.refs[attribute]).value.trim();
        });
        this.props.onCreate(newEmployee);

        // clear out the dialog's inputs
        this.props.attributes.forEach(attribute => {
            React.findDOMNode(this.refs[attribute]).value = '';
        });

        // Navigate away from the dialog to hide it.
        window.location = "#";
    }

    render() {
        // Gather all entity fields
        var inputs = this.props.attributes.map(attribute =>
            <p key={attribute}>
                <input type="text" placeholder={attribute} ref={attribute} className="field" />
            </p>
        );

        return (
            <div>
                <a href="#createEmployee">Create new employee</a>

                <div id="createEmployee" className="modalDialog">
                    <div>
                        <a href="#" title="Close" className="close">X</a>
                        <h2>New employee</h2>

                        <form>
                            {inputs}
                            <button onClick={this.handleSubmit}>Create</button>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

// Class for handling employee updates
class UpdateDialog extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        var updatedEmployee = {};

        // fill updatedEmployee with form fields, update
        this.props.attributes.forEach(attribute => {
            updatedEmployee[attribute] = React.findDOMNode(this.refs[attribute]).value.trim();
        });
        this.props.onUpdate(this.props.employee, updatedEmployee);

        // hide dialog
        window.location = "#";
    }

    render() {
        var inputs = this.props.attributes.map(attribute =>
            <p key={this.props.employee.entity[attribute]}>
                <input type="text" placeholder={attribute}
                       defaultValue={this.props.employee.entity[attribute]}
                       ref={attribute} className="field" />
            </p>
        );

        var dialogId = "updateEmployee-" + this.props.employee.entity._links.self.href;

        return (
            <div key={this.props.employee.entity._links.self.href}>
                <a href={"#" + dialogId}>Update</a>
                <div id={dialogId} className="modalDialog">
                    <div>
                        <a href="#" title="Close" className="close">X</a>

                        <h2>Update an employee</h2>

                        <form>
                            {inputs}
                            <button onClick={this.handleSubmit}>Update</button>
                        </form>
                    </div>
                </div>
            </div>
        )
    }

}

// Class for listing employees with pagination
class EmployeeList extends React.Component {

    constructor(props) {
        super(props);
        this.handleNavFirst = this.handleNavFirst.bind(this);
        this.handleNavPrev = this.handleNavPrev.bind(this);
        this.handleNavNext = this.handleNavNext.bind(this);
        this.handleNavLast = this.handleNavLast.bind(this);
        this.handleInput = this.handleInput.bind(this);
    }

    // handle
    handleInput(e) {
        e.preventDefault();
        // gather value from input
        var pageSize = React.findDOMNode(this.refs.pageSize).value;

        if (/^[0-9]+$/.test(pageSize)) {
            // if number update employee table
            this.props.updatePageSize(pageSize);
        } else {
            // if not - remove last symbol
            React.findDOMNode(this.refs.pageSize).value =
                pageSize.substring(0, pageSize.length - 1);
        }
    }

    // handle pagination links
    handleNavFirst(e){
        e.preventDefault();
        this.props.onNavigate(this.props.links.first.href);
    }
    handleNavPrev(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.prev.href);
    }
    handleNavNext(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.next.href);
    }
    handleNavLast(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.last.href);
    }

    render() {
        // gather employees
        var employees = this.props.employees.map(employee =>
                            <Employee key={employee.entity._links.self.href}
                                      employee={employee}
                                      attributes={this.props.attributes}
                                      onUpdate={this.props.onUpdate}
                                      onDelete={this.props.onDelete}/>
        );

        // prepare links to follow
        var navLinks = [];
        if ("first" in this.props.links) {
            navLinks.push(<button key="first" onClick={this.handleNavFirst}>&lt;&lt;</button>);
        }
        if ("prev" in this.props.links) {
            navLinks.push(<button key="prev" onClick={this.handleNavPrev}>&lt;</button>);
        }
        if ("next" in this.props.links) {
            navLinks.push(<button key="next" onClick={this.handleNavNext}>&gt;</button>);
        }
        if ("last" in this.props.links) {
            navLinks.push(<button key="last" onClick={this.handleNavLast}>&gt;&gt;</button>);
        }

        return (
            <div>
                <div>
                    entities per page:
                    <input ref="pageSize" defaultValue={this.props.pageSize} onInput={this.handleInput}/>
                </div>

                <table>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Description</th>
                        <th/>
                        <th/>
                    </tr>
                    {employees}
                </table>
                <div>
                    {navLinks}
                </div>
            </div>
        )
    }
}

// Class for rendering single employeee in EmployeeList's table
class Employee extends React.Component {

    constructor(props) {
        super(props);
        this.handleDelete = this.handleDelete.bind(this);
    }

    handleDelete() {
        this.props.onDelete(this.props.employee);
    }

    render() {
        return (
            <tr>
                <td>{this.props.employee.entity.firstName}</td>
                <td>{this.props.employee.entity.lastName}</td>
                <td>{this.props.employee.entity.description}</td>
                <td>
                    <UpdateDialog employee={this.props.employee}
                                  attributes={this.props.attributes}
                                  onUpdate={this.props.onUpdate}/>
                </td>
                <td>
                    <button onClick={this.handleDelete}>Delete</button>
                </td>
            </tr>
        )
    }
}

// Render <App /> instead of <div id="react"/>
React.render(<App />, document.getElementById('react'));
