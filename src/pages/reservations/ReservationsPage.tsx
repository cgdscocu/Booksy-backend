import { useState } from 'react'
import { useQuery } from 'react-query'
import { Calendar, MapPin, Users, Clock, CheckCircle, XCircle, AlertCircle } from 'lucide-react'
import { reservationsAPI } from '../../lib/api'
import { useAuth } from '../../contexts/AuthContext'
import { Card, CardContent, CardHeader, CardTitle } from '../../components/ui/Card'
import Button from '../../components/ui/Button'
import { formatCurrency, formatDate } from '../../lib/utils'
import type { Reservation } from '../../types'

export default function ReservationsPage() {
  const { user } = useAuth()
  const [filter, setFilter] = useState<'all' | 'upcoming' | 'past' | 'cancelled'>('all')

  const { data: reservations = [], isLoading, error } = useQuery<Reservation[]>(
    'reservations',
    reservationsAPI.getAll
  )

  // Filter reservations by user and status
  const userReservations = reservations.filter(reservation => 
    reservation.userId === user?.userId
  )

  const filteredReservations = userReservations.filter(reservation => {
    const today = new Date()
    const checkInDate = new Date(reservation.checkInDate)
    
    switch (filter) {
      case 'upcoming':
        return checkInDate >= today && reservation.status !== 'CANCELLED'
      case 'past':
        return checkInDate < today && reservation.status !== 'CANCELLED'
      case 'cancelled':
        return reservation.status === 'CANCELLED'
      default:
        return true
    }
  })

  const getStatusIcon = (status: string) => {
    switch (status.toLowerCase()) {
      case 'confirmed':
        return <CheckCircle className="h-5 w-5 text-green-600" />
      case 'pending':
        return <Clock className="h-5 w-5 text-yellow-600" />
      case 'cancelled':
        return <XCircle className="h-5 w-5 text-red-600" />
      default:
        return <AlertCircle className="h-5 w-5 text-gray-600" />
    }
  }

  const getStatusColor = (status: string) => {
    switch (status.toLowerCase()) {
      case 'confirmed':
        return 'bg-green-100 text-green-800'
      case 'pending':
        return 'bg-yellow-100 text-yellow-800'
      case 'cancelled':
        return 'bg-red-100 text-red-800'
      default:
        return 'bg-gray-100 text-gray-800'
    }
  }

  if (isLoading) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="animate-spin rounded-full h-32 w-32 border-b-2 border-primary-600"></div>
      </div>
    )
  }

  if (error) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="text-center">
          <h2 className="text-2xl font-bold text-gray-900 mb-2">Something went wrong</h2>
          <p className="text-gray-600">Please try again later</p>
        </div>
      </div>
    )
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="mb-8">
          <h1 className="text-3xl font-bold text-gray-900 mb-4">My Reservations</h1>
          
          {/* Filter Tabs */}
          <div className="flex space-x-1 bg-gray-100 p-1 rounded-lg w-fit">
            {[
              { key: 'all', label: 'All' },
              { key: 'upcoming', label: 'Upcoming' },
              { key: 'past', label: 'Past' },
              { key: 'cancelled', label: 'Cancelled' }
            ].map((tab) => (
              <button
                key={tab.key}
                onClick={() => setFilter(tab.key as any)}
                className={`px-4 py-2 rounded-md text-sm font-medium transition-colors ${
                  filter === tab.key
                    ? 'bg-white text-primary-600 shadow-sm'
                    : 'text-gray-600 hover:text-gray-900'
                }`}
              >
                {tab.label}
              </button>
            ))}
          </div>
        </div>

        {filteredReservations.length > 0 ? (
          <div className="space-y-6">
            {filteredReservations.map((reservation) => (
              <Card key={reservation.reservationId} className="overflow-hidden">
                <CardContent className="p-6">
                  <div className="flex flex-col lg:flex-row lg:items-center lg:justify-between">
                    <div className="flex-1">
                      <div className="flex items-center justify-between mb-4">
                        <h3 className="text-xl font-semibold">
                          {reservation.hotel?.name || 'Hotel Name'}
                        </h3>
                        <div className="flex items-center space-x-2">
                          {getStatusIcon(reservation.status)}
                          <span className={`px-2 py-1 rounded-full text-xs font-medium ${getStatusColor(reservation.status)}`}>
                            {reservation.status}
                          </span>
                        </div>
                      </div>

                      <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-4">
                        <div className="flex items-center text-gray-600">
                          <Calendar className="h-4 w-4 mr-2" />
                          <div>
                            <div className="text-sm">Check-in</div>
                            <div className="font-medium">{formatDate(reservation.checkInDate)}</div>
                          </div>
                        </div>
                        
                        <div className="flex items-center text-gray-600">
                          <Calendar className="h-4 w-4 mr-2" />
                          <div>
                            <div className="text-sm">Check-out</div>
                            <div className="font-medium">{formatDate(reservation.checkOutDate)}</div>
                          </div>
                        </div>

                        <div className="flex items-center text-gray-600">
                          <Users className="h-4 w-4 mr-2" />
                          <div>
                            <div className="text-sm">Room Type</div>
                            <div className="font-medium">
                              {reservation.roomType?.name || 'Standard Room'}
                            </div>
                          </div>
                        </div>
                      </div>

                      {reservation.hotel?.address && (
                        <div className="flex items-center text-gray-600 mb-4">
                          <MapPin className="h-4 w-4 mr-2" />
                          <span className="text-sm">{reservation.hotel.address}</span>
                        </div>
                      )}
                    </div>

                    <div className="lg:ml-6 lg:text-right">
                      <div className="text-2xl font-bold text-primary-600 mb-2">
                        {formatCurrency(reservation.totalPrice)}
                      </div>
                      <div className="text-sm text-gray-600 mb-4">
                        Total amount
                      </div>
                      
                      <div className="flex flex-col space-y-2">
                        {reservation.status.toLowerCase() === 'confirmed' && 
                         new Date(reservation.checkInDate) > new Date() && (
                          <Button variant="outline\" size="sm">
                            Modify Booking
                          </Button>
                        )}
                        
                        {reservation.status.toLowerCase() !== 'cancelled' && 
                         new Date(reservation.checkInDate) > new Date() && (
                          <Button variant="destructive" size="sm">
                            Cancel Booking
                          </Button>
                        )}
                        
                        <Button variant="ghost" size="sm">
                          View Details
                        </Button>
                      </div>
                    </div>
                  </div>
                </CardContent>
              </Card>
            ))}
          </div>
        ) : (
          <Card>
            <CardContent className="p-12 text-center">
              <Calendar className="h-16 w-16 text-gray-400 mx-auto mb-4" />
              <h3 className="text-lg font-medium text-gray-900 mb-2">
                No reservations found
              </h3>
              <p className="text-gray-600 mb-6">
                {filter === 'all' 
                  ? "You haven't made any reservations yet."
                  : `No ${filter} reservations found.`
                }
              </p>
              <Button onClick={() => window.location.href = '/hotels'}>
                Browse Hotels
              </Button>
            </CardContent>
          </Card>
        )}
      </div>
    </div>
  )
}