/** @jsx React.DOM */

var MessageList = React.createClass({
    render: function() {
        var responsesNode = this.props.datas.map(function (value) {
            return <Message value={value.message} type={value.type} />;
        });
        return <div class="conversation">{responsesNode}</div>;
    }
});

var Message = React.createClass({
    render: function() {
        return (
            <div class="message">
               <p>{this.props.value}</p>
            </div>
            );
    }
});

var ConversationBox = React.createClass({
    getInitialState: function() {
        return {conversationID: 0,
        		clientID: 0,
        		responses: [],
        		input: ''};
    },
    componentDidMount: function() {
        this.begin();
    },
    begin: React.autoBind(function() {
        $.ajax({
            url: this.props.startUrl,
            success: function(data) {
                this.setState({responses: data.listOfString,
                	           conversationID: data.conversationID,
                	           clientID: data.clientID,
                	           input: ''});
            }.bind(this)
        });
    }),
    loadResponses: React.autoBind(function() {
    	$.ajax({
            url: "/SimpleSpringMVCApp/getConversation",
            success: function(data) {
                this.setState({responses: data.listOfString,
                	           conversationID: data.conversationID,
                	           clientID: data.clientID,
                	           input: ''});
            }.bind(this)
        });
    }),
    handleInputSubmit: React.autoBind(function(input) {
        var responses = this.state.responses;
        responses.push({message: input.answer, type: "input"});
        this.setState({data: responses});
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: this.props.url,
            type: 'POST',
            dataType: "json",
            data: JSON.stringify(input),
            success: function(data) {
            	this.setState({responses: data.listOfString,
			     	           conversationID: data.conversationID,
			     	           clientID: data.clientID,
			     	           input: ''});
            }.bind(this)
        });
    }),
    render: function() {
        return (
            <div class="commentBox">
                <h1>ChatBox</h1>
                <MessageList datas={this.state.responses} />
                <ChatForm conversationID={this.state.conversationID}
                		  clientID={this.state.clientID}
                		  onInputSubmit={this.handleInputSubmit} />
            </div>
            );
    }
});

var ChatForm = React.createClass({
    handleSubmit: React.autoBind(function() {
        var input = this.refs.answer.getDOMNode().value.trim();
        var conversationID = this.refs.conversationID.getDOMNode().value.trim();
        var clientID = this.refs.clientID.getDOMNode().value.trim();
        if (input.length === 0) {
            return false;
        }
        this.props.onInputSubmit({conversationID: conversationID,
        						  clientID: clientID,
        						  answer: input});
        this.refs.answer.getDOMNode().value = '';
        return false;
    }),
    render: function() {
        return (
            <form className="conversationForm" onSubmit={this.handleSubmit}>
            	<input type="hidden" ref="conversationID" value={this.props.conversationID} />
            	<input type="hidden" ref="clientID" value={this.props.clientID} />
                <input type="text" placeholder="Your answer" ref="answer" />
                <input type="submit" value="Post" />
            </form>
            );
    }
});

React.renderComponent(
	<ConversationBox startUrl="/SimpleSpringMVCApp/startConversation"
					 url="/SimpleSpringMVCApp/converse" 
				     pollInterval={2000} />,
    document.getElementById('container')
);